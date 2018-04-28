NAME = swing-overflow
SHA1 = $(shell git rev-parse HEAD)

.PHONY: all build test auth_heroku tag_heroku push_heroku

all: build

build:
	docker build -t $(NAME):$(SHA1) .

test:
	docker run $(DOCKER_RUN_TEST_PARAMS) $(NAME):$(SHA1) ./gradlew --info --stacktrace -x test

auth_heroku:
	docker login -e $(DOCKER_EMAIL) -u $(DOCKER_USER) -p $(DOCKER_PASS) registry.heroku.com

tag_heroku: auth_heroku
	docker tag $(NAME):$(SHA1) registry.heroku.com/$(HEROKU_APP)/web

push_heroku: tag_heroku
	docker push registry.heroku.com/$(HEROKU_APP)/web