package by.mapper.map_interfaces.rank;

import by.database.entity.Rank;
import by.dto.rank_dto.RankDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T23:17:55+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class RankToDtoMapperImpl implements RankToDtoMapper {

    @Override
    public RankDto rankToDto(Rank rank) {
        if ( rank == null ) {
            return null;
        }

        RankDto.RankDtoBuilder rankDto = RankDto.builder();

        rankDto.id( rank.getId() );
        rankDto.rankName( rank.getRankName() );
        rankDto.salary( rank.getSalary() );

        return rankDto.build();
    }
}
