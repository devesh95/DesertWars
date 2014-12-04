public class Konami {
	
	int[] konami = {38, 38, 40, 40, 37, 39, 37, 39, 66, 65};
	int latestkeypress = 0;
	
	/**
	 * Checks if the key is part of a running sequence of characters - namely
	 * the Konami code: UP, UP, DOWN, DOWN, LEFT, RIGHT, LEFT, RIGHT, B, A
	 * @param keyPressed
	 * @return
	 */
	boolean wasItKonami(int keyPressed) {
		if(keyPressed == konami[latestkeypress]) {
			latestkeypress++;

			if(latestkeypress == konami.length) {
				latestkeypress = 0; //reset
				return true;
			}
		}
		else {
			latestkeypress = 0;
		}

		return false;
	}
}
