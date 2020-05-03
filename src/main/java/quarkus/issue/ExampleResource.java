package quarkus.issue;

import net.codecrete.qrbill.generator.Address;
import net.codecrete.qrbill.generator.Bill;
import net.codecrete.qrbill.generator.QRBill;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_SVG_XML)
    public byte[] hello() {
        // Setup bill
        Bill bill = new Bill();
        bill.setAccount("CH4431999123000889012");
        bill.setAmountFromDouble(199.95);
        bill.setCurrency("CHF");

        // Set creditor
        Address creditor = new Address();
        creditor.setName("Robert Schneider AG");
        creditor.setAddressLine1("Rue du Lac 1268/2/22");
        creditor.setAddressLine2("2501 Biel");
        creditor.setCountryCode("CH");
        bill.setCreditor(creditor);

        // more bill data
        bill.setReference("210000000003139471430009017");
        bill.setUnstructuredMessage("Abonnement für 2020");

        // Set debtor
        Address debtor = new Address();
        debtor.setName("Pia-Maria Rutschmann-Schnyder");
        debtor.setAddressLine1("Grosse Marktgasse 28");
        debtor.setAddressLine2("9400 Rorschach");
        debtor.setCountryCode("CH");
        bill.setDebtor(debtor);

        // Generate QR bill
        return QRBill.generate(bill);
    }
}
