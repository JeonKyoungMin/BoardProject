package com.board.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.beans.ContentBean;
import com.board.beans.Criteria;
import com.board.beans.PageBean;
import com.board.beans.UserBean;
import com.board.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardServiceImpl implements BoardService {

	@Value("${path.upload}")
	private String pathUpload;
	
	@Value("${page.listcnt}")
	private int pageListcnt;
	
	@Value("${page.paginationcnt}")
	private int pagePaginationcnt;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 파일 첨부
	 */
	private String saveUploadFile(MultipartFile uploadFile) {
		
		String fileName = System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
		
		try {
			uploadFile.transferTo(new File(pathUpload + "/" + fileName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileName;
	};
	
	@Autowired
	private BoardDao boardDao;

	/**
	 * <pre>
	 * 처리내용 : 게시글 작성
	 */
	@Override
	public void insertContent(ContentBean contentBean) {
		
		contentBean.setContentWriterIdx(loginUserBean.getUserIdx());
		MultipartFile uploadFile = contentBean.getUploadFile();
		
		if (uploadFile.getSize() > 0) {
			//임시파일 
			String fileName = saveUploadFile(uploadFile);

			//ContentBean file 객체에 임시파일 set
			contentBean.setContentFile(fileName);
		}
		
		boardDao.insertContent(contentBean);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 정보 가져오기
	 */
	@Override
	public String getBoardInfo(int boardInfoIdx) {
		String result = boardDao.getBoardInfo(boardInfoIdx);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 리스트 가져오기 
	 */
	@Override
	public List<ContentBean> getContentList(int boardInfoIdx, int page) {
		int start = (page - 1) * pageListcnt;
		RowBounds rowBounds = new RowBounds(start, pageListcnt);
		
		List<ContentBean> result = boardDao.getContentList(boardInfoIdx, rowBounds);

		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 정보 가져오기
	 */
	@Override
	public ContentBean getContentInfo(int contentIdx) {
		ContentBean result = boardDao.getContentInfo(contentIdx);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 수정
	 */
	@Override
	public void modifyContent(ContentBean contentBean) {
		
		MultipartFile uploadFile = contentBean.getUploadFile();
		
		if (uploadFile.getSize() > 0) {
			String fileName = saveUploadFile(uploadFile);
			contentBean.setContentFile(fileName);
		}
		
		boardDao.modifyContent(contentBean);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 삭제
	 */
	@Override
	public void deleteContent(int contentIdx) {
		
		boardDao.deleteContent(contentIdx);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 삭제 
	 */
	@Override
	public PageBean getContentCnt(int contentBoardIdx, int currentPage) {
	
		int contentCnt = boardDao.getContentCnt(contentBoardIdx);
		
		PageBean pageBean = new PageBean(contentCnt, currentPage, pageListcnt, pagePaginationcnt);
		
		return pageBean;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 
	 */
	@Override
	public void hitByIdx(ContentBean contentBean) {
		boardDao.hitByIdx(contentBean);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 
	 */
	@Override
	public Criteria selectPrevNextNum(Criteria cri) {
		Criteria result = boardDao.selectPrevNextNum(cri);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 페이징을 포함한 게시글 리스트 가져오기
	 */
	@Override
	public List<ContentBean> listPage(Criteria cri) {
		
		List<ContentBean> result = boardDao.listPage(cri);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 전체 갯수 가져오기
	 */
	@Override
	public int totalCount(Criteria cri) {
		int result = boardDao.totalCount(cri);
		
		return result;
	}
}

