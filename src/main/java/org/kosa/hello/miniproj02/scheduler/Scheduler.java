package org.kosa.hello.miniproj02.scheduler;

import lombok.RequiredArgsConstructor;
import org.kosa.hello.miniproj02.entity.UserVO;
import org.kosa.hello.miniproj02.login.mapper.LoginMapper;
import org.kosa.hello.miniproj02.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private final LoginMapper loginMapper;
    private final ScheduledExecutor scheduledExecutor;
    private final UserMapper userMapper;

    /**
     * [Scheduler 설정 참고사항]
     * 초 : 0~59
     * 분 : 0~59
     * 시 : 0~23
     * 일 : 1~31
     * 월 : 1~12 혹은 JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC
     * 요일 : 1~7 혹은 SUN MON TUE WED THU FRI SAT
     * <p>
     * * : 모든 값
     * ? : 설정 없음 (일과 요일만 사용 가능)
     * , : 배열 ex) 1,5,8 : 1,5,8에만
     * - : 앞부터 뒤까지 ex) 1-3 : 1부터 3까지
     * / : 앞부터 뒤마다 ex) 1/3 : 1부터 매3마다 1,4,7,11...
     */
    public void unLockUser(LocalDateTime requestTime, String userId) {
        UserVO userVO = loginMapper.login(new UserVO(userId));
        if(userVO.getLogin_count() >= 3){
            LocalDateTime now = LocalDateTime.now();
            long delay = Duration.between(now, requestTime.plusMinutes(20)).toMillis();
            Runnable run = () -> {
                   loginMapper.unLock(userId);
               };
            scheduledExecutor.scheduledExecutorService().schedule(run, 5, TimeUnit.SECONDS);
        }}
}
