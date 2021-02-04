package icia.kotlin.beans;

import lombok.Data;

@Data
public class Movie {
	private String sCode;
	private String mvCode;
	private String mvName;
	private String mvGrade;
	private String mvStatus;
	private String mvScreen;
	private String mvTime;
	private String mvImage;
	private String mvComments;
	private String mvDate;
	private String mvThCode; /* step4를 위해 추가 */
	private String mvDateTime; /* step4를 위해 추가 */
}