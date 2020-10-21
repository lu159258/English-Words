package com.lu.usermanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lu
 * @email 1157076680@qq.com
 * @date 2020-06-12 15:51:11
 */
@Data
@TableName("l_words")
public class WordsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String word;
	/**
	 * 
	 */
	private String translate;
	/**
	 * 
	 */
	private String exp;
	/**
	 * 
	 */
	private String speak;

}
