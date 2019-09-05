package com.kh.board.model.vo;

import java.sql.Date;

public class Board {	
		
		private int boardNo;
		private String boardTitle;
		private String boardWriter;
		private String boardContent;
		private String boardOriginalFilename;
		private String boardRenamedFilename;
		private Date boardData;
		private int readCount;
		
		public Board() {
		
		}

		public Board(int boardNo, String boardTitle, String boardWriter, String boardContent,
				String boardOriginalFilename, String boardRenamedFilename, Date boardData, int readCount) {
			super();
			this.boardNo = boardNo;
			this.boardTitle = boardTitle;
			this.boardWriter = boardWriter;
			this.boardContent = boardContent;
			this.boardOriginalFilename = boardOriginalFilename;
			this.boardRenamedFilename = boardRenamedFilename;
			this.boardData = boardData;
			this.readCount = readCount;
		}

		public int getBoardNo() {
			return boardNo;
		}

		public void setBoardNo(int boardNo) {
			this.boardNo = boardNo;
		}

		public String getBoardTitle() {
			return boardTitle;
		}

		public void setBoardTitle(String boardTitle) {
			this.boardTitle = boardTitle;
		}

		public String getBoardWriter() {
			return boardWriter;
		}

		public void setBoardWriter(String boardWriter) {
			this.boardWriter = boardWriter;
		}

		public String getBoardContent() {
			return boardContent;
		}

		public void setBoardContent(String boardContent) {
			this.boardContent = boardContent;
		}

		public String getBoardOriginalFilename() {
			return boardOriginalFilename;
		}

		public void setBoardOriginalFilename(String boardOriginalFilename) {
			this.boardOriginalFilename = boardOriginalFilename;
		}

		public String getBoardRenamedFilename() {
			return boardRenamedFilename;
		}

		public void setBoardRenamedFilename(String boardRenamedFilename) {
			this.boardRenamedFilename = boardRenamedFilename;
		}

		public Date getBoardData() {
			return boardData;
		}

		public void setBoardData(Date boardData) {
			this.boardData = boardData;
		}

		public int getReadCount() {
			return readCount;
		}

		public void setReadCount(int readCount) {
			this.readCount = readCount;
		}
		
		

}
