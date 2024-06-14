package com.example.scheduleappserver;

import com.example.scheduleappserver.entity.Comment;
import com.example.scheduleappserver.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityTest {

    @Test
    @DisplayName("userEntity 생성 테스트")
    void createUserEntity(){
        // given
        String nickname = "nickname";
        String username = "username";
        String password = "password";
        LocalDateTime createdAt = LocalDateTime.now();

        Comment comment = new Comment();
        comment.setUserName("user");
        comment.setContent("content");
        comment.setCreatedAt(createdAt);

        Comment comment2 = new Comment();
        comment2.setUserName("user2");
        comment2.setContent("content2");
        comment2.setCreatedAt(createdAt);

        List<Comment> comments = Arrays.asList(comment, comment2);

        // when
        User user = User.builder()
                .nickname(nickname)
                .username(username)
                .password(password)
                .createdAt(createdAt)
                .comments(comments)
                .build();

        comment.commentUser(user);
        comment2.commentUser(user);

        // then
        assertThat(user.getNickname()).isEqualTo(nickname);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getCreatedAt()).isEqualTo(createdAt);
        assertThat(user.getComments()).hasSize(2);
        assertThat(user.getComments().get(0).getUser()).isEqualTo(user);
        assertThat(user.getComments().get(1).getUser()).isEqualTo(user);
    }
}
