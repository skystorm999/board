package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	
	public int insertBoard(BoardDTO param);
	
	public BoardDTO selectBoardDetail(Long idx);
	
	public int updateBoard(BoardDTO param);
	
	public int deleteBoard(Long idx);
	
	public List<BoardDTO> selectBoardList();
	
	public int selectBoardTotalCount();
}
