import java.util.Scanner;

public class Financialstatementcreator {
    private static Scanner Scan = new Scanner(System.in);

    public static void main(String[] args) {
        // Main program loop
        while (true) {
            System.out.println("\n--- Financial Statement Generator ---");
            System.out.println("Enter 1 to begin inputting data, or -1 to quit:");
            double trans = Scan.nextDouble();  
            if (trans == -1) {
                break;
            }

            // Run the statement calculations sequentially
            double retainedEarnings = Retain();
            double netIncome = Income();
            double balanceSheetMetric = Balance();
            double operatingCash = Flow();
            double investingCash = invest();
            double financingCash = Financing();

            System.out.println("\n Data capture loop completed successfully!");
        }
    }

    private static double Income() { // Income statement method
        System.out.println("Input the company/organization name:");
        Scan.nextLine(); // Clear scanner buffer from nextDouble()
        String company = Scan.nextLine();
        
        double totalrevenue = 0;   
        while (true) {
            System.out.println("Input the Revenue generated for the financial year (input -1 when done):");
            double rev = Scan.nextDouble(); // Fixed: Added 'double' type declaration
            if (rev == -1) {
                break;   
            }
            totalrevenue += rev;
        }

        double totalexpenses = 0;
        while (true) {
            System.out.println("Input the expenses for the financial year (input -1 when done):");
            double exp = Scan.nextDouble();
            if (exp == -1) {
                break;
            }
            totalexpenses += exp;
        }
        
        // Net Income = Revenue minus Expenses
        return totalrevenue - totalexpenses;
    }

    private static double Retain() { // Statement of retained earnings method
        double totalretainedearnings = 0;
        while (true) {
            System.out.println("Input the beginning retained earnings (enter -1 when done):");
            double beg = Scan.nextDouble();
            if (beg == -1) {
                break;
            }
            totalretainedearnings += beg;
        }

        double totalnetincome = 0;
        while (true) {
            System.out.println("Input the net income for the year (enter -1 when done):");
            double inc = Scan.nextDouble();
            if (inc == -1) {
                break; 
            }
            totalnetincome += inc;
        }

        double totaldividends = 0;
        while (true) {
            System.out.println("Input the dividends for the year (enter -1 when done):"); 
            double div = Scan.nextDouble(); // Fixed: Added 'double' type declaration
            if (div == -1) {
                break;
            }
            totaldividends += div;
        }

        // Ending Retained Earnings = Beginning + Net Income - Dividends
        return totalretainedearnings + totalnetincome - totaldividends;
    }

    private static double Balance() { // The balance sheet method
        double totalassets = 0;
        while (true) {
            System.out.println("Input the yearly Assets (enter -1 when done):");
            double ass = Scan.nextDouble();
            if (ass == -1) {
                break;
            }
            totalassets += ass;
        }
        
        double totalliabilities = 0;
        while (true) {
            System.out.println("Input the yearly liabilities (enter -1 when done):");
            double lia = Scan.nextDouble();
            if (lia == -1) {
                break;   
            }
            totalliabilities += lia;
        }

        double totalsharecapital = 0;
        while (true) {
            System.out.println("Input the yearly share capital (enter -1 when done):");
            double shar = Scan.nextDouble();
            if (shar == -1) {
                break;   
            }
            totalsharecapital += shar;
        }

        double totalretainede = 0;
        while (true) {
            System.out.println("Input the retained earnings (enter -1 when done):");
            double ret = Scan.nextDouble();
            if (ret == -1) {
                break;
            }
            totalretainede += ret;
        }
        return totalassets + totalliabilities + totalretainede + totalsharecapital;
    }

    private static double Flow() { // Cash Flow Statement Method (Operating)
        double totaloperatingact = 0;
        while (true) {
            System.out.println("Input cash inflows from operating activities (enter -1 when done):"); 
            double inf = Scan.nextDouble();
            if (inf == -1) {
                break; 
            }
            totaloperatingact += inf;
        }

        double totalcashout = 0;
        while (true) {
            System.out.println("Input cash outflows from operating activities (enter -1 when done):");  
            double out = Scan.nextDouble();
            if (out == -1) {
                break;
            }
            totalcashout += out;
        }
        return totaloperatingact - totalcashout;
    }

    private static double invest() { // Investing Activities
        double totalinflows = 0;
        while (true) {
            System.out.println("Input cash inflows from investing activities (enter -1 when done):");   
            double inv = Scan.nextDouble();
            if (inv == -1) {
                break; 
            }  
            totalinflows += inv;
        }

        double totaloutflow = 0;
        while (true) {
            System.out.println("Input cash outflows from investing activities (enter -1 when done):");
            double inv2 = Scan.nextDouble();
            if (inv2 == -1) {
                break;
            }
            totaloutflow += inv2;
        }
        return totalinflows - totaloutflow;
    }

    private static double Financing() { // Financing Activities
        double totalfinance = 0;
        while (true) {
            System.out.println("Input cash inflows from financing activities (enter -1 when done):");
            double fin = Scan.nextDouble();
            if (fin == -1) {
                break;
            } 
            totalfinance += fin;    
        }

        double totaloutfinance = 0;
        while (true) {
            System.out.println("Input cash outflows from financing activities (enter -1 when done):");
            double fin2 = Scan.nextDouble();
            if (fin2 == -1) {
                break;  
            }
            totaloutfinance += fin2;
        }
        return totalfinance - totaloutfinance; 
    }
}