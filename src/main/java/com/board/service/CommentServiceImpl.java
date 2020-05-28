package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.CommentDTO;
import com.board.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	/*
	 * CommentMapper 빈(Bean) 선언
	 */
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public boolean registerComment(CommentDTO params) {
		
		/* 쿼리 실행 결과 */
		int queryResult = 0;
		
		if(params.getIdx() == null) {
			queryResult = commentMapper.insertComment(params);
		} else {
			queryResult = commentMapper.updateComment(params);
		}
		
		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteComment(Long idx) {
		int queryResult = 0;
		
		CommentDTO comment = commentMapper.selectCommentDetail(idx);
		
		if(comment != null && "N".equals(comment.getDeleteYn())) {
			queryResult = commentMapper.deleteComment(idx);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<CommentDTO> getCommentList(CommentDTO params) {
		
		/* 댓글 목록을 담을 비어있는 리스트 */
		List<CommentDTO> commentList = Collections.emptyList();
		
		/**
		 * TODO => 페이징 적용하기
		 */

		/* 전체 댓글 수 */
		int commentTotalCount = commentMapper.selectCommentTotalCount(params);
		
		if(commentTotalCount > 0) {
			commentList = commentMapper.selectCommentList(params);
		}
		return commentList;
	}

}
