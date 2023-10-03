package de._125m125.supersetapi.samples.springbootstarter._3.superset;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SupersetMapper {
  SupersetEmbedDetailsDto mapEmbedDetails(SupersetClientProperties properties);
}
