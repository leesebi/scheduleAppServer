package com.example.scheduleappserver.service;

import com.example.scheduleappserver.dto.CommentRequestDto;
import com.example.scheduleappserver.dto.CommentResponseDto;
import com.example.scheduleappserver.dto.CommentUpdateRequestDto;
import com.example.scheduleappserver.entity.Comment;
import com.example.scheduleappserver.entity.Schedule;
import com.example.scheduleappserver.repository.CommentRepository;
import com.example.scheduleappserver.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 저장
    public void save(Long scheduleId, CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto); // 서비스에서 해야됨
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new IllegalArgumentException("")
        );
        schedule.getComments().add(comment);

        commentRepository.save(comment);
        scheduleRepository.save(schedule);
    }

    // 삭제
    public CommentResponseDto update(Long scheduleId, Long commentId, CommentUpdateRequestDto requestDto) {
        Comment request = findScheduleAndComment(scheduleId, commentId);

        // 객체에 set을 이용해 값을 수정
        request.setContent(requestDto.getContent());

        commentRepository.save(request);
        // 값을 응답dto에 넣고 리턴
        return new CommentResponseDto(request);
    }

    public void delete(Long scheduleId, Long commentId) {
        Comment request = findScheduleAndComment(scheduleId, commentId);
        commentRepository.delete(request);
    }

    // 스케줄과 댓글 찾기 기능
    public Comment findScheduleAndComment(Long scheduleId, Long commentId) {
        // schedulId과 일치하는 schedule을 가져온다.
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("")
        );

        // schedule에 있는 comment들을 list로 받아온다.
        List<Comment> commentList = schedule.getComments();

        // 수정하는 commentId값을 위의 list에서 찾고 객체에 넘겨준다.
        Comment request = (Comment) commentList.stream().filter(comments -> comments.getId().equals(commentId)).findFirst().orElseThrow(
                () -> new IllegalArgumentException("id를 찾을 수 없습니다.")
        );

        return request;
    }
}
