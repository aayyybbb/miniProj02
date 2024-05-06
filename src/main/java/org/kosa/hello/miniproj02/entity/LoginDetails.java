package org.kosa.hello.miniproj02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kosa.hello.miniproj02.scheduler.Scheduler;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;

@Data
@AllArgsConstructor
@Builder
public class LoginDetails implements UserDetails {
    private Scheduler scheduler;

    private UserVO userVO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();

        Arrays.stream(userVO.getRole().split(",")).forEach(role -> collections.add(new SimpleGrantedAuthority("ROLE_" + role.trim())));

        return collections;
    }

    @Override
    public String getPassword() {
        return userVO.getPwd();
    }

    @Override
    public String getUsername() {
        return userVO.getUser_id();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (userVO.getLocked_at() != null) {
            return false;
        } else if (userVO.getLogin_count() == 3) {
            scheduler.unLockUser(LocalDateTime.now(), userVO.getUser_id());
            System.err.println("잠금해제 스케줄 등록 :" + userVO.getUser_id());
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
