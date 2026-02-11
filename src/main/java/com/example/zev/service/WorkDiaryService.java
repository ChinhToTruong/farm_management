package com.example.zev.service;

import com.example.zev.entity.Notification;
import com.example.zev.entity.User;
import com.example.zev.entity.WorkDiary;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.UserRepository;
import com.example.zev.repository.WorkDiaryRepository;
import com.example.zev.utils.AuthUtils;
import com.example.zev.utils.ExportExcelUtils;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final UserRepository userRepository;

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

    public String exportExcel() throws IOException {
        Long userId = AuthUtils.getUserId();
        List<Object[]> rows = workDiaryRepository.findByUserId(userId);
        List<String> headers = List.of(
            "Tên công việc",
            "Nội dung công việc",
            "Ngày thực hiện",
            "Vụ mùa",
            "Người thực hiện",
            "Cây trồng",
            "Đàn vật nuôi",
            "Trạng thái xử lý"
        );

        return ExportExcelUtils.exportExcelBase64("Công việc hàng ngày", headers, rows);
    }


    @Scheduled(cron = "0 */1 * * * ?")
    public void notifyWorkForUser() {
        // 1. Lấy danh sách user có công việc PENDING
        List<User> users = userRepository
            .findUsersHavePendingWork();

        for (User user : users) {

            // 2. Đếm số công việc được phân cho user
            long totalWork = workDiaryRepository
                .countWorkDiaryByUserIdAndStatus(
                    user.getId(),
                    "PENDING"
                );

            // 3. Notify cho từng user
            sendNotify(user, totalWork);
        }
    }

    private void sendNotify(User user, long totalWork) {

        if (totalWork == 0) {
            return;
        }

        Notification notification = new Notification();
        notification.setRead(false);
        notification.setTitle("Thông báo");
        notification.setMessage("Bạn"
            + " có " + totalWork + " công việc đang chờ xử lý");
        notification.setUserId(user.getId());
        notificationService.notifyUser(user.getId(), notification);
        System.out.println(
            "Bạn"
                + " có " + totalWork + " công việc đang chờ xử lý"
        );

        // WebSocket / Firebase / Email xử lý tại đây
    }

}
