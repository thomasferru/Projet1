module annuaireIsika.Annuaire {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires itextpdf;
	
	exports annuaireIsika.Annuaire.Front;
	exports annuaireIsika.Annuaire;

	opens annuaireIsika.Annuaire to javafx.graphics;
	opens annuaireIsika.Annuaire.back to javafx.base;
}
