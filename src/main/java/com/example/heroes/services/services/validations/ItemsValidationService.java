package com.example.heroes.services.services.validations;

import com.example.heroes.services.models.items.ItemCreateServiceModel;

public interface ItemsValidationService {

    boolean isValid(ItemCreateServiceModel serviceModel);

}
