package org.example.controller;
import io.javalin.http.Context;
import org.example.model.Loans;
import org.example.service.LoansService;
import java.util.List;

    public class LoansController {
        // create service object
        LoansService loansService;

        public LoansController(){
            //instantiate service object
            this.loansService = new LoansService();
        }
                /*
                 * POST /loans
                 * JSON body:
                 * {
                 *   "userId": 1,
                 *   "loanType": "Car loan",
                 *   "status": pending
                 * }
                 */
            // ctx extractor interface ro create POST Loan
            public void addLoan(Context ctx){
            Loans loan = ctx.bodyAsClass(Loans.class);

            Loans addedLoan = loansService.addLoan(loan);
            ctx.json(addedLoan);
            }

            /*
             * GET /loan?userId=1
             *
             * If a 'userId' query param is present, returns only that user’s loans.
             * Otherwise, returns all loans.  (optional approach)
             */

    public void getUserLoans(Context ctx){
        String userIdParam = ctx.queryParam("user_id");
    if(userIdParam != null) {

            //Filter by user Id
            int userId = Integer.parseInt(userIdParam);

            List<Loans>userLoans = loansService.getUserLoans(userId);
            ctx.json(userLoans);
            }else{
            //return all loans is no userId is specified
            List<Loans> allLoans = loansService.getAllLoans();
            ctx.json(allLoans);
        }

            /*
             * GET /loans
             * Returns JSON array of ALL todos
             * (This can be used if you prefer a simpler dedicated endpoint.)
             */

        }
        public void getAllLoans( Context ctx) {
        List<Loans> allLoans = loansService.getAllLoans();
        ctx.json(allLoans);
        }

            // Updates loans
        public void updateLoan(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("id"));

        Loans loan = new Loans();
        loan.setId(userId);
        loan.setLoanType(loan.getLoanType());
        loan.setAmount(loan.getAmount());
        loan.setStatus(loan.getStatus());

        loansService.updateLoan(userId);
        ctx.status(200).json("{\"message\":\"loan updated\"}");
        }

        // Deletes loans

        public void deleteLoan( Context ctx) {
            int userId = Integer.parseInt(ctx.pathParam("id"));
            loansService.deleteLoan(userId);
            ctx.status(200).json("{\"message\":\"loan deleted\"}");
        }
    }