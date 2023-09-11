package annuaireIsika.Annuaire.Front;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import annuaireIsika.Annuaire.back.Stagiaire;
import javafx.scene.control.TableView;

public class GeneratePDF {
	public static void exportToPdf(TableV tableV, String outputPath) {
		try {
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(outputPath));
			document.open();

			PdfPTable pdfTable = new PdfPTable(5); // Créez un tableau avec 5 colonnes

			// Ajoutez des en-têtes de colonnes
			PdfPCell cell = new PdfPCell(new com.itextpdf.text.Phrase("Nom"));
			pdfTable.addCell(cell);

			cell = new PdfPCell(new com.itextpdf.text.Phrase("Prénom"));
			pdfTable.addCell(cell);

			cell = new PdfPCell(new com.itextpdf.text.Phrase("Département"));
			pdfTable.addCell(cell);

			cell = new PdfPCell(new com.itextpdf.text.Phrase("Promotion"));
			pdfTable.addCell(cell);

			cell = new PdfPCell(new com.itextpdf.text.Phrase("Année d'entrée"));
			pdfTable.addCell(cell);

			// Obtenez la TableView de l'objet TableV
			TableView<Stagiaire> tableView = tableV.getTableView();

			// Ajoutez le contenu de la TableView au PDF
			for (Stagiaire stagiaire : tableView.getItems()) {
				pdfTable.addCell(stagiaire.getNom());
				pdfTable.addCell(stagiaire.getPrenom());
				pdfTable.addCell(stagiaire.getDepartement());
				pdfTable.addCell(stagiaire.getPromotion());
				pdfTable.addCell(String.valueOf(stagiaire.getAnneeFormation()));
			}

			document.add(pdfTable);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

}
