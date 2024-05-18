package codex.evaluation.model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Component
public class PdfModel {
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD);
        font.setColor(Color.WHITE);

        cell.setBorderWidth(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setPhrase(new Phrase("N°", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DESIGNATIONS", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Unite", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Qte", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PU", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("TOTAL", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, List<DetailDevis> detailDevisList) {
        Font font = FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL);
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setBorderWidth(1);

        // Formatter pour séparer les milliers
        DecimalFormat df = new DecimalFormat("#,##0");

        for (DetailDevis detailDevis: detailDevisList) {
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPhrase(new Phrase(String.valueOf(detailDevis.getCode()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(detailDevis.getTravauxNom(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(detailDevis.getUnite(), font));
            table.addCell(cell);

            // Alignement à droite pour les nombres
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            cell.setPhrase(new Phrase(df.format(detailDevis.getQte()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(df.format(detailDevis.getPu()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(String.format("%.2f",(detailDevis.getPu() * detailDevis.getQte()), font)));
            table.addCell(cell);
        }
    }

    private void writeTableHeaderPaiement(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD);
        font.setColor(Color.WHITE);

        cell.setBorderWidth(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setPhrase(new Phrase("REF", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Montant", font));
        table.addCell(cell);
    }


    private void writeDataPaiement(PdfPTable table, List<Paiement> paiements) {
        Font font = FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL);
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setBorderWidth(1);

        for (Paiement paiement: paiements) {
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPhrase(new Phrase(String.valueOf(paiement.getRef_paiement()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(paiement.getDate()), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(String.format("%.2f", paiement.getMontant()), font));
            table.addCell(cell);
        }
    }


    public void export(HttpServletResponse response, List<DetailDevis> detailDevisList, List<Paiement> paiementList) throws DocumentException, IOException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        double sommeDevis = 0;
        for (DetailDevis dd: detailDevisList) {
            sommeDevis+=(dd.getPu() * dd.getQte());
        }

        double sommePayer = 0;
        for (Paiement pp: paiementList) {
            sommePayer+=pp.getMontant();
        }

        Document document = new Document((PageSize.A4));
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.black);

        Image logo = Image.getInstance("classpath:static/assets/img/logo.png");
        logo.setAlignment(Image.ALIGN_CENTER);
        logo.scaleToFit(150, Float.MAX_VALUE);

        document.add(logo);
        String titreDevis = "DEVIS";
        if (detailDevisList.size()>0) {
            titreDevis+= " "+detailDevisList.get(0).getClientDevis().getRef();
        }
        Paragraph p = new Paragraph(titreDevis, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 3.0f, 1.0f, 1.5f, 1.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, detailDevisList);

        PdfPTable tablePaiement = new PdfPTable(3);
        tablePaiement.setWidthPercentage(100f);
        tablePaiement.setWidths(new float[] {2.0f, 3.0f, 5.0f});
        tablePaiement.setSpacingBefore(10);

        writeTableHeaderPaiement(tablePaiement);
        writeDataPaiement(tablePaiement, paiementList);


        Font fontDetailsDevis = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.black);

        Paragraph titleDetails = new Paragraph("Details Devis", fontDetailsDevis);
        titleDetails.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(titleDetails);

        document.add(table);

        Font fontSomme = FontFactory.getFont(FontFactory.TIMES);
        font.setSize(12);
        font.setColor(Color.black);
        Paragraph TotalDevis = new Paragraph("TOTAL : "+numberFormat.format(sommeDevis), fontSomme);
        TotalDevis.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(TotalDevis);

        Paragraph titlePayement = new Paragraph("Liste Payement", fontDetailsDevis);
        titlePayement.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(titlePayement);

        document.add(tablePaiement);
        Paragraph TotalPayement = new Paragraph("TOTAL : "+numberFormat.format(sommePayer), fontSomme);
        TotalPayement.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(TotalPayement);

        document.close();
    }
}
