package cn.owntt.service;


import org.springframework.stereotype.Service;

import cn.owntt.entity.Music;

import java.util.List;

/**
 * 经纪人持久层接口
 * @author LJ
 *
 */
@Service
public interface IMusicDao {

	IMusicDao getInstance();

	List<Music> selectMusics(int page, String name);
	
	int count(String name);
	
	boolean insert(Music m);
	
	Music selectMusicById(int id);
}
