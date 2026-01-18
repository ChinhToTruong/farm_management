package com.example.zev.service;

import com.example.zev.entity.Notification;
import com.example.zev.entity.User;
import com.example.zev.entity.WorkDiary;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.WorkDiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkDiaryService extends CrudServiceImpl<WorkDiary> {

    private final UserService userService;
    private final WorkDiaryRepository workDiaryRepository;
    private final PlantService plantService;
    private final CropSeasonService cropSeasonService;
    private final AnimalBatchService animalBatchService;
    private final NotificationService notificationService;

    @Override
    public WorkDiary create(WorkDiary entity) throws BusinessException {
        User user = userService.findEntity(entity.getUserId());

        WorkDiary work = super.create(entity);
        Notification notification = new Notification();
        notification.setRead(false);
        notification.setTitle("Thông báo");
        notification.setMessage("Bạn có 1 công việc mới");
        notification.setUserId(user.getId());
        notificationService.notifyUser(user.getId(), notification);
        return work;
    }
}
