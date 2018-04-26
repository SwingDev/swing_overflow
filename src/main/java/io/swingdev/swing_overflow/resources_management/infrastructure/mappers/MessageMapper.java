package io.swingdev.swing_overflow.resources_management.infrastructure.mappers;

import io.swingdev.swing_overflow.resources_management.api.dto.MessageDTO;
import io.swingdev.swing_overflow.resources_management.domain.Message;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageMapper {
    private static Converter<String, Date> toDate = ctx -> new Date((long) Float.parseFloat(ctx.getSource()) * 1000);
    private static Converter<Date, String> toTimestamp = ctx -> Float.toString(ctx.getSource().getTime() / 1000);
    private TypeMap<MessageDTO, Message> dtoToMessageMap;
    private TypeMap<Message, MessageDTO> messageToDtoMap;

    public MessageMapper(ModelMapper modelMapper) {
        dtoToMessageMap = modelMapper.createTypeMap(MessageDTO.class, Message.class);
        dtoToMessageMap.addMappings(mapping -> mapping.using(toDate).map(src -> src.ts, Message::changeDate));

        messageToDtoMap = modelMapper.createTypeMap(Message.class, MessageDTO.class);
        messageToDtoMap.addMappings(
            mapping -> mapping
                .using(toTimestamp)
                .map(Message::getDate, (destination, value) -> destination.ts = (String) value)
        );
    }

    public MessageDTO toDto(Message message) {
        return messageToDtoMap.map(message);
    }

    public Message toMessage(MessageDTO messageDTO) {
        return dtoToMessageMap.map(messageDTO);
    }
}
