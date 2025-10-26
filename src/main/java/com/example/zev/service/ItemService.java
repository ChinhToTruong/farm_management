package com.example.zev.service;

import com.example.zev.entity.Category;
import com.example.zev.entity.Item;
import com.example.zev.entity.Location;
import com.example.zev.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService extends CrudServiceImpl<Item> {

    private final CategoryService categoryService;
    private final LocationService locationService;

    @Override
    public Item create(Item item) throws BusinessException {
        Category category = categoryService.findById(item.getCategory().getId());
        Location location = locationService.findById(item.getLocation().getId());
        item.setCategory(category);
        item.setLocation(location);

        return super.create(item);
    }
}
