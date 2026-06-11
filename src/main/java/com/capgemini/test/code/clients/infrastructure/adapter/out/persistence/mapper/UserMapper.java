package com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.mapper;

import com.capgemini.test.code.clients.domain.model.Room;
import com.capgemini.test.code.clients.domain.model.User;
import com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.jpa.entity.RoomEntity;
import com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "room.users", ignore = true)
    User toDomain(UserEntity user);
}
