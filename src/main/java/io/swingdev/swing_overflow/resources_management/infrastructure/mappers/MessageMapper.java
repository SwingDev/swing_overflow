//package io.swingdev.swing_overflow.resources_management.infrastructure.mappers;
//
//import io.swingdev.swing_overflow.resources_management.api.dto.MessageDTO;
//import io.swingdev.swing_overflow.resources_management.domain.Message;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeMap;
//
//import java.util.Date;
//
//public class MessageMapper {
//    public MessageMapper(ModelMapper modelMapper) {
//        TypeMap<MessageDTO, Message> typeMap = modelMapper.createTypeMap(MessageDTO.class, Message.class);
//        typeMap.addMapping(src -> {
//            long timestamp = (long) Float.parseFloat(src.ts) * 1000;
//            return new Date(timestamp);
//        }, Message::setDate);
//    }
//}
