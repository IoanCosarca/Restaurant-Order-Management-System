package DataAccessLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class CreateReports {

    public void Report1(List<Order> IntervalOrders)
    {
        try
        {
            Document report1 = new Document();
            PdfWriter.getInstance(report1, new FileOutputStream("Report1.pdf"));
            report1.open();
            for (Order order : IntervalOrders)
            {
                Paragraph paragraph = new Paragraph(order.toString());
                report1.add(paragraph);
            }
            report1.close();
        }
        catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Report2(List<MenuItem> popularProducts)
    {
        try
        {
            Document report2 = new Document();
            PdfWriter.getInstance(report2, new FileOutputStream("Report2.pdf"));
            report2.open();
            for (MenuItem menuItem : popularProducts)
            {
                Paragraph paragraph = new Paragraph(menuItem.computeTitle() + " was ordered " + menuItem.computeTimesOrdered() + " times");
                report2.add(paragraph);
            }
            report2.close();
        }
        catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Report3(int numberOfTimes, double value, List<Order> specificOrders)
    {
        try
        {
            Document report3 = new Document();
            PdfWriter.getInstance(report3, new FileOutputStream("Report3.pdf"));
            report3.open();
            Paragraph header = new Paragraph("The clients that have ordered more than " + numberOfTimes + " times, orders that sum up more than " + value + " are:");
            report3.add(header);
            System.out.println(specificOrders.size());
            for (Order orderInfo : specificOrders)
            {
                Paragraph paragraph = new Paragraph(orderInfo.getClient().getUsername() + " (" + orderInfo.getClient().getOrdersPlaced() + " times)");
                report3.add(paragraph);
            }
            report3.close();
        }
        catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Report4(Date date, List<MenuItem> orderedProducts)
    {
        try
        {
            Document report4 = new Document();
            PdfWriter.getInstance(report4, new FileOutputStream("Report4.pdf"));
            report4.open();
            Paragraph header = new Paragraph("In date " + date + " the following products have been ordered");
            report4.add(header);
            System.out.println(orderedProducts.size());
            for (MenuItem menuItem : orderedProducts)
            {
                Paragraph paragraph = new Paragraph(menuItem.computeTitle() + " was ordered " + menuItem.computeTimesOrdered() + " times");
                report4.add(paragraph);
            }
            report4.close();
        }
        catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
