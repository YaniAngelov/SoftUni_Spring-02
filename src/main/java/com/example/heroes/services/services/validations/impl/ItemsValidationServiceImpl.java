package com.example.heroes.services.services.validations.impl;

import com.example.heroes.services.models.items.ItemCreateServiceModel;
import com.example.heroes.services.services.validations.ItemsValidationService;
import org.springframework.stereotype.Service;

@Service
public class ItemsValidationServiceImpl implements ItemsValidationService {

    @Override
    public boolean isValid(ItemCreateServiceModel serviceModel) {
        return serviceModel != null &&
                serviceModel.getName() != null &&
                serviceModel.getSlot() != null &&
                serviceModel.getAttack() > 0;
    }
}
