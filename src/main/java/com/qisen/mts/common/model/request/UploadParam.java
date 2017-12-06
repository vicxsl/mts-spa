/**
 * 
 */
package com.qisen.mts.common.model.request;

/**
 * @author forbr
 *
 */
public class UploadParam {
	private String dir;
	private String name;
	private String variations;
	private Boolean trim;
	private Integer maxSizes;
	private String base64;
	private String realName;
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVariations() {
		return variations;
	}
	public void setVariations(String variations) {
		this.variations = variations;
	}
	public Boolean getTrim() {
		return trim;
	}
	public void setTrim(Boolean trim) {
		this.trim = trim;
	}
	public Integer getMaxSizes() {
		return maxSizes;
	}
	public void setMaxSizes(Integer maxSizes) {
		this.maxSizes = maxSizes;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
