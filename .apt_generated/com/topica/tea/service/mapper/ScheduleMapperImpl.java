package com.topica.tea.service.mapper;

import com.topica.tea.domain.Schedule;
import com.topica.tea.service.dto.ScheduleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-01T10:03:55+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public Schedule toEntity(ScheduleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setId( dto.getId() );
        schedule.setDayOfWeek( dto.getDayOfWeek() );
        schedule.setTimeZone( dto.getTimeZone() );

        return schedule;
    }

    @Override
    public ScheduleDTO toDto(Schedule entity) {
        if ( entity == null ) {
            return null;
        }

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId( entity.getId() );
        scheduleDTO.setDayOfWeek( entity.getDayOfWeek() );
        scheduleDTO.setTimeZone( entity.getTimeZone() );

        return scheduleDTO;
    }

    @Override
    public List<Schedule> toEntity(List<ScheduleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Schedule> list = new ArrayList<Schedule>();
        for ( ScheduleDTO scheduleDTO : dtoList ) {
            list.add( toEntity( scheduleDTO ) );
        }

        return list;
    }

    @Override
    public List<ScheduleDTO> toDto(List<Schedule> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ScheduleDTO> list = new ArrayList<ScheduleDTO>();
        for ( Schedule schedule : entityList ) {
            list.add( toDto( schedule ) );
        }

        return list;
    }
}
