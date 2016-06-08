package com.dameiren.app.ui.base;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aloneter
 * @ClassName: Result
 * @Description: 网络请求结果
 * @date 2014-12-18
 * @Version 1.0
 */
public class BaseResult<T> {

	@SerializedName(value= "status")
	public int status;
	@SerializedName(value= "message")
	public String message;
	@SerializedName(value= "lastTime")
	public Long lastTime;
	@SerializedName(value= "data")
	public T data;

}
