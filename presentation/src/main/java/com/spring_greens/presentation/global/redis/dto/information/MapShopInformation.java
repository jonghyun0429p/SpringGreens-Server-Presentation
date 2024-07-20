package com.spring_greens.presentation.global.redis.dto.information;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Getter
public class MapShopInformation extends ShopInformation<MapProductInformation> {
    public MapShopInformation() {super();}
}