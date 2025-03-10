package org.example.service;

import org.example.dao.LoansDao;
import org.example.model.Loans;

import java.util.List;


public class LoansService {
    LoansDao loansDao;

        public LoansService(){
            this.loansDao = new LoansDao();
        }

            /**
             * Create/add a new Loan.
             */

            public Loans addLoan(Loans loan) {
                return loansDao.addLoan(loan);
            }
            /**
             * Retrieve specific user loans.
             */
            public List<Loans> getUserLoans(Integer userId) {
                return  loansDao.getUserLoans(userId);
            }
            /**
             * Retrieve all Loans.
             */
            public List<Loans> getAllLoans() {
                return loansDao.getAllLoans();
            }

            /*
             * Update an existing loan.
             */
            public void updateLoan(int userId) {
            return loansDao.updateLoan(userId);
            }
            /*
             * Delete a loan by ID.
             */
            public List<Loans> deleteLoan(Integer useId){
                return loansDao.deleteLoan(useId);
            }


}
