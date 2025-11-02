package com.example.zev.service;

import com.example.zev.entity.AnimalBatch;
import com.example.zev.entity.FeedLog;
import com.example.zev.entity.User;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.FeedLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedLogService extends CrudServiceImpl<FeedLog> {

    private final FeedLogRepository feedLogRepository;
    private final UserService userService;
    private final AnimalBatchService animalBatchService;

    @Override
    public FeedLog create(FeedLog feedLog) throws BusinessException {
        User user = userService.findEntity(feedLog.getUser().getId());
        AnimalBatch animalBatch = animalBatchService.findById(feedLog.getAnimalBatch().getId());
        feedLog.setUser(user);
        feedLog.setAnimalBatch(animalBatch);
        return feedLogRepository.save(feedLog);
    }
}
