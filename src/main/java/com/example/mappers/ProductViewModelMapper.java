package com.example.mappers;

import com.example.models.api.product.PostpaidProductModelDto;
import com.example.models.api.product.PrepaidProductModelDto;
import com.example.models.api.product.ProductModelDto;
import com.example.models.view.PostpaidProductViewModel;
import com.example.models.view.PrepaidProductViewModel;
import com.example.models.view.ProductViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;

// пример маппинга multiple subclasses
@Mapper
public interface ProductViewModelMapper {
    @SubclassMapping(source = PrepaidProductModelDto.class, target = PrepaidProductViewModel.class)
    @SubclassMapping(source = PostpaidProductModelDto.class, target = PostpaidProductViewModel.class)
    ProductViewModel from(ProductModelDto productModelDto);
}
