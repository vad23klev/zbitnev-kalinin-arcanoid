﻿package view;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

/**
 * Класс визуального представления игры
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameView extends GameEngine {

	@Override
	public GameObject getGame(int arg0) {
		
		switch (arg0) {
			case 0: 
				return new ScreenGame(this);
			case 1:
				return new ScreenMenu(this);
		}
		
		// TODO: Выброс исключения
		return null;
	}

}
