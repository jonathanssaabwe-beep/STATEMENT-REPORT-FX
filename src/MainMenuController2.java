import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController2 {

    // ==========================================
    // 1. FXML UI FIELDS
    // ==========================================

    // Income Statement
    @FXML private TextField CompanyNameinput;
    @FXML private ComboBox<String> categoryBox;
    @FXML private TextField Amountinput;
    @FXML private Label companyField;
    @FXML private Label revenueLabel;
    @FXML private Label expenseLabel;
    @FXML private Label netIncomeLabel;

    // Retained Earnings
    @FXML private TextField begRetainedInput;
    @FXML private TextField netIncomeInput;
    @FXML private TextField dividendsInput;
    @FXML private Label endingRetainedLabel;

    // Balance Sheet 
    @FXML private TextField assetInput;
    @FXML private Label totalAssetsLabel;
    @FXML private TextField liabilityInput;
    @FXML private TextField shareCapitalInput;
    @FXML private TextField retainedEarningsInput;
    @FXML private Label totalLiabilitiesEquityLabel;

    // Cash Flow
    @FXML private TextField opInflowField;
    @FXML private TextField opOutflowField;
    @FXML private Label opNetLabel;
    @FXML private TextField invInflowField;
    @FXML private TextField invOutflowField;
    @FXML private Label invNetLabel;
    @FXML private TextField finInflowField;
    @FXML private TextField finOutflowField;
    @FXML private Label finNetLabel;
    @FXML private Label grandTotalCashLabel;


    // ==========================================
    // 2. MATHEMATICAL STATE COUNTERS 
    // ==========================================

    // Income Statement Math Counters ---
    private double totalRevenue = 0;
    private double totalExpenses = 0;

    // Balance Sheet Math Counters
    private double totalAssets = 0;
    private double totalLiabilities = 0;

    // Cash Flow Statement Math Counters
    private double totalOpInflows = 0;
    private double totalOpOutflows = 0;
    private double totalInvInflows = 0;
    private double totalInvOutflows = 0;
    private double totalFinInflows = 0;
    private double totalFinOutflows = 0;


    // ==========================================
    // 3. INITIALIZATION
    // ==========================================

        @FXML
    public void initialize() {
        if (categoryBox != null) {
            categoryBox.setItems(FXCollections.observableArrayList("Revenue", "Expense"));
        }
    }


    
    // 4. INCOME STATEMENT LOGIC
  

    @FXML
    public void handleCompanyNameEnter(ActionEvent event) {
        companyField.setText("Company Name: " + CompanyNameinput.getText());
        categoryBox.requestFocus();
    }

    @FXML
    public void handleCategorySelect(ActionEvent event) {
        Amountinput.requestFocus();
    }

    @FXML
    public void handleAddItem(ActionEvent event) {
        try {
            String selectedCategory = categoryBox.getValue();
            if (selectedCategory == null) {
                System.out.println("ERROR: Select 'Revenue' or 'Expense' first.");
                return;
            }

            double amount = Double.parseDouble(Amountinput.getText());

            if (selectedCategory.equals("Revenue")) {
                totalRevenue += amount;
                System.out.println("SUCCESS: Tracked Revenue of $" + amount);
            } else if (selectedCategory.equals("Expense")) {
                totalExpenses += amount;
                System.out.println("SUCCESS: Tracked Expense of $" + amount);
            }

            revenueLabel.setText("Total Revenue: $" + totalRevenue);
            expenseLabel.setText("Total Expenses: $" + totalExpenses);
            
            double netIncome = totalRevenue - totalExpenses;
            netIncomeLabel.setText("Net Income: $" + netIncome);

            Amountinput.clear();
            categoryBox.requestFocus();

        } catch (NumberFormatException e) {
            System.out.println("ERROR: Please input a valid numerical value in the amount field.");
        }
    }


  
    // 5. RETAINED EARNINGS LOGIC
  

    @FXML
    public void handleBegRetainedEnter(ActionEvent event) {
        netIncomeInput.requestFocus();
    }

    @FXML
    public void handleNetIncomeEnter(ActionEvent event) {
        dividendsInput.requestFocus();
    }

    @FXML
    public void handleCalculateRetained(ActionEvent event) {
        try {
            double beginning = begRetainedInput.getText().isEmpty() ? 0 : Double.parseDouble(begRetainedInput.getText());
            double netIncome = netIncomeInput.getText().isEmpty() ? 0 : Double.parseDouble(netIncomeInput.getText());
            double dividends = dividendsInput.getText().isEmpty() ? 0 : Double.parseDouble(dividendsInput.getText());

            double endingBalance = beginning + netIncome - dividends;

            endingRetainedLabel.setText("Ending Retained Earnings: $" + endingBalance);
            System.out.println("SUCCESS: Calculated Ending Retained Earnings of $" + endingBalance);

        } catch (NumberFormatException e) {
            System.out.println("ERROR: Please check that all inputs are valid numbers.");
        }
    }


   
    // 6. BALANCE SHEET 
 

    @FXML
    public void handleAddAsset(ActionEvent event) {
        try {
            double amount = Double.parseDouble(assetInput.getText());
            totalAssets += amount;
            totalAssetsLabel.setText("Total Assets: $" + totalAssets);
            
            assetInput.clear();
            assetInput.requestFocus();
            System.out.println("SUCCESS: Added Asset worth $" + amount);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter a valid asset amount.");
        }
    }

    @FXML
    public void handleAddLiability(ActionEvent event) {
        try {
            double amount = Double.parseDouble(liabilityInput.getText());
            totalLiabilities += amount;
            
            liabilityInput.clear();
            shareCapitalInput.requestFocus();
            System.out.println("SUCCESS: Added Liability worth $" + amount);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter a valid liability amount.");
        }
    }

    @FXML
    public void handleShareCapitalEnter(ActionEvent event) {
        retainedEarningsInput.requestFocus();
    }

    @FXML
    public void handleVerifyBalanceSheet(ActionEvent event) {
        try {
            double shareCapital = shareCapitalInput.getText().isEmpty() ? 0 : Double.parseDouble(shareCapitalInput.getText());
            double retainedEarnings = retainedEarningsInput.getText().isEmpty() ? 0 : Double.parseDouble(retainedEarningsInput.getText());

            double totalLiabilitiesEquity = totalLiabilities + shareCapital + retainedEarnings;
            totalLiabilitiesEquityLabel.setText("Total Liabilities & Equity: $" + totalLiabilitiesEquity);

            if (Double.compare(totalAssets, totalLiabilitiesEquity) == 0) {
                System.out.println("SUCCESS: Balance Sheet balances! ($" + totalAssets + " = $" + totalLiabilitiesEquity + ")");
            } else {
                System.out.println("WARNING: Balance Sheet out of balance! Assets: $" + totalAssets + " | Liabilities & Equity: $" + totalLiabilitiesEquity);
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Please check that Share Capital and Retained Earnings contain valid numbers.");
        }
    }


    
    // 7. CASH FLOW STATEMENT 

    @FXML
    public void handleOpInflowEnter(ActionEvent event) {
        opOutflowField.requestFocus();
    }

    @FXML
    public void handleAddOperatingCash(ActionEvent event) {
        try {
            double inflow = opInflowField.getText().isEmpty() ? 0 : Double.parseDouble(opInflowField.getText());
            double outflow = opOutflowField.getText().isEmpty() ? 0 : Double.parseDouble(opOutflowField.getText());

            totalOpInflows += inflow;
            totalOpOutflows += outflow;

            double netOp = totalOpInflows - totalOpOutflows;
            opNetLabel.setText("Net Cash from Operating: $" + netOp);

            opInflowField.clear();
            opOutflowField.clear();
            
            invInflowField.requestFocus(); 
            updateGrandTotal();
            System.out.println("SUCCESS: Tracked Operating Cash. Net: $" + netOp);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Check operating inputs.");
        }
    }

    @FXML
    public void handleInvInflowEnter(ActionEvent event) {
        invOutflowField.requestFocus();
    }

    @FXML
    public void handleAddInvestingCash(ActionEvent event) {
        try {
            double inflow = invInflowField.getText().isEmpty() ? 0 : Double.parseDouble(invInflowField.getText());
            double outflow = invOutflowField.getText().isEmpty() ? 0 : Double.parseDouble(invOutflowField.getText());

            totalInvInflows += inflow;
            totalInvOutflows += outflow;

            double netInv = totalInvInflows - totalInvOutflows;
            invNetLabel.setText("Net Cash from Investing: $" + netInv);

            invInflowField.clear();
            invOutflowField.clear();

            finInflowField.requestFocus(); 
            updateGrandTotal();
            System.out.println("SUCCESS: Tracked Investing Cash. Net: $" + netInv);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Check investing inputs.");
        }
    }

    @FXML
    public void handleFinInflowEnter(ActionEvent event) {
        finOutflowField.requestFocus();
    }

    @FXML
    public void handleAddFinancingCash(ActionEvent event) {
        try {
            double inflow = finInflowField.getText().isEmpty() ? 0 : Double.parseDouble(finInflowField.getText());
            double outflow = finOutflowField.getText().isEmpty() ? 0 : Double.parseDouble(finOutflowField.getText());

            totalFinInflows += inflow;
            totalFinOutflows += outflow;

            double netFin = totalFinInflows - totalFinOutflows;
            finNetLabel.setText("Net Cash from Financing: $" + netFin);

            finInflowField.clear();
            finOutflowField.clear();

            updateGrandTotal();
            System.out.println("SUCCESS: Tracked Financing Cash. Net: $" + netFin);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Check financing inputs.");
        }
    }

    private void updateGrandTotal() {
        double netOp = totalOpInflows - totalOpOutflows;
        double netInv = totalInvInflows - totalInvOutflows;
        double netFin = totalFinInflows - totalFinOutflows;
        
        double netChange = netOp + netInv + netFin;
        grandTotalCashLabel.setText("NET INCREASE/DECREASE IN CASH: $" + netChange);
    }


    // 8. SCENE NAVIGATION & SYSTEM CONTROLS
    

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent nextView = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(nextView));
            window.show();
        } catch (IOException e) {
            System.out.println("Error loading screen layout: " + fxmlFile);
            e.printStackTrace();
        }
    }

    @FXML
    public void handleReturnToMenu(ActionEvent event) {
        System.out.println("SUCCESS: Returning to Dashboard.");
        switchScene(event, "Financialstatement.fxml");
    }

    @FXML
    public void handleIncomeMenu(ActionEvent event) {
        switchScene(event, "INCOME_STATEMENT.fxml");
    }

    @FXML
    public void handleRetainedMenu(ActionEvent event) {
        switchScene(event, "RETAINED_EARNINGS.fxml");
    }

    @FXML
    public void handleBalanceMenu(ActionEvent event) {
        switchScene(event, "balance_sheet.fxml");
    }

    @FXML
    public void handleCashFlowMenu(ActionEvent event) {
        switchScene(event, "CASHFLOW_GENERATOR.fxml");
    }

    @FXML
    public void handleLogOut(ActionEvent event) {
        System.out.println("SUCCESS: Closing application.");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
}