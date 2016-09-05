package me.matt11matthew.atherialrunes.game.enums;

public enum Unicodes {
	
	SECTION_SIGN('§'),
	RIGHT_DOUBLE_POINTER('»'),
	LEFT_DOUBLE_POINTER('«'),
	BULLET('•'),
	SMALL_RIGHT_ARROW('→'),
	SMALL_LEFT_ARROW('←'),
	LOADING_ARROW_RIGHT('↻'),
	LOADING_ARROW_LEFT('↺'),
	LIGHT_SHADED('░'),
	MEDIUM_SHADED('▒'),
	DARK_SHADED('▓'),
	RIGHT_PLAY_ARROW('►'),
	LEFT_PLAY_ARROW('◄'),
	BIG_ARROW_RIGHT('➔'),
	HYPIXEL_STAR('⛦'),
	EMPTY_CHECKBOX('☐'),
	X_CHECKBOX('☒'),
	CHECK_CHECKBOX('☑'),
	COMMON_HEART('❤'),
	X('☓'),
	DEBUG_ARROW('➤'),
	CLEAN_STAR('★'),
	SKULL('☠'),
	BOX('▉');

	
	Character character;
	
	Unicodes(Character character) {
		this.character = character;
	}
	
	public String get() {
		return this.character.toString();
		
	}
}