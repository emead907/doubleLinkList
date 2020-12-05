/**
 * Runs a set of black box tests for lists that 
 * implement IndexedUnsortedList ADT. 
 * This is a set of black box tests that should 
 * work for any implementation.
 * 
 * @author CS221
 */
public class RunIndexedUnsortedTests
{
	// TODO: make sure using correct list when running tests 
	
	// Which list to use in tests:
	// { goodList, arrayList, singleLinkedList, doubleLinkedList, wrappedDLL }
	
	private final static ListType LIST_TYPE = ListType.doubleLinkedList; 

	// total test count
	private static int totalTests = 0; 

	// instance variables for tracking test results
	private static int passes = 0;
	private static int failures = 0;
	private static int total = 0;

	/** 
	 * Run tests
	 * @param args - not used in this test suite
	 */
	public static void main(String[] args)
	{
		runTests();
		
		// report final verdict
		printFinalSummary();
	}
	
	/** 
	 * Run tests to confirm required functionality of implementation
	*/
	private static void runTests()
	{		
		// Tests for given change scenarios
		Test_1_constructor_emptyList constructor_emptyList = new Test_1_constructor_emptyList();
		printDescription(constructor_emptyList.getDescription());
		constructor_emptyList.run(LIST_TYPE); 
		totalTests += constructor_emptyList.getNumTests(); 
		printLine(); 
			
		Test_6_A_addToFrontB_BA A_addToFrontB_BA = new Test_6_A_addToFrontB_BA();
		printDescription(A_addToFrontB_BA.getDescription());
		A_addToFrontB_BA.run(LIST_TYPE); 
		totalTests += A_addToFrontB_BA.getNumTests(); 
		printLine(); 
		
		Test_39_ABC_remove1_AC ABC_remove1_AC = new Test_39_ABC_remove1_AC();
		printDescription(ABC_remove1_AC.getDescription());
		ABC_remove1_AC.run(LIST_TYPE); 
		totalTests += ABC_remove1_AC.getNumTests(); 
		printLine(); 
		
		// TODO: Add instances of your test classes here 
		/**
		Test_2_addToFrontA_A addToFrontA_A = new Test_2_addToFrontA_A();
		printDescription(addToFrontA_A.getDescription());
		addToFrontA_A.run(LIST_TYPE); 
		totalTests += addToFrontA_A.getNumTests(); 
		printLine(); 
		*/
		
		
		Test_7_A_addToRearB_AB A_addToRearB_AB = new Test_7_A_addToRearB_AB();
		printDescription(A_addToRearB_AB.getDescription());
		A_addToRearB_AB.run(LIST_TYPE); 
		totalTests += A_addToRearB_AB.getNumTests(); 
		printLine(); 
		
		
		
		Test_8_A_addAfterBA_AB A_addAfterBA_AB = new Test_8_A_addAfterBA_AB();
		printDescription(A_addAfterBA_AB.getDescription());
		A_addAfterBA_AB.run(LIST_TYPE); 
		totalTests += A_addAfterBA_AB.getNumTests(); 
		printLine(); 
		
		
		Test_9_A_addB_AB A_addB_AB = new Test_9_A_addB_AB();
		printDescription(A_addB_AB.getDescription());
		A_addB_AB.run(LIST_TYPE); 
		totalTests += A_addB_AB.getNumTests(); 
		printLine(); 
		
		Test_10_A_add0B_BA A_add0B_BA = new Test_10_A_add0B_BA();
		printDescription(A_add0B_BA.getDescription());
		A_add0B_BA.run(LIST_TYPE); 
		totalTests += A_add0B_BA.getNumTests(); 
		printLine(); 
		
		
		Test_17_AB_addToFrontC_CAB AB_addToFrontC_CAB = new Test_17_AB_addToFrontC_CAB();
		printDescription(AB_addToFrontC_CAB.getDescription());
		AB_addToFrontC_CAB.run(LIST_TYPE); 
		totalTests += AB_addToFrontC_CAB.getNumTests(); 
		printLine();
		
		Test_18_AB_addToRearC_ABC AB_addToRearC_ABC = new Test_18_AB_addToRearC_ABC();
		printDescription(AB_addToRearC_ABC.getDescription());
		AB_addToRearC_ABC.run(LIST_TYPE); 
		totalTests += AB_addToRearC_ABC.getNumTests(); 
		printLine();
		
		Test_19_AB_addAfterCA_ACB AB_addAfterCA_ACB = new Test_19_AB_addAfterCA_ACB();
		printDescription(AB_addAfterCA_ACB.getDescription());
		AB_addAfterCA_ACB.run(LIST_TYPE); 
		totalTests += AB_addAfterCA_ACB.getNumTests(); 
		printLine();
		
		Test_20_AB_addAfterCB_ABC AB_addAfterCB_ABC = new Test_20_AB_addAfterCB_ABC();
		printDescription(AB_addAfterCB_ABC.getDescription());
		AB_addAfterCB_ABC.run(LIST_TYPE); 
		totalTests += AB_addAfterCB_ABC.getNumTests(); 
		printLine();
		
		Test_21_AB_addC_ABC AB_addC_ABC = new Test_21_AB_addC_ABC();
		printDescription(AB_addC_ABC.getDescription());
		AB_addC_ABC.run(LIST_TYPE); 
		totalTests += AB_addC_ABC.getNumTests(); 
		printLine();
		
		Test_22_AB_add0C_CAB AB_add0C_CAB = new Test_22_AB_add0C_CAB();
		printDescription(AB_add0C_CAB.getDescription());
		AB_add0C_CAB.run(LIST_TYPE); 
		totalTests += AB_add0C_CAB.getNumTests(); 
		printLine();
		
		Test_23_AB_add1C_ACB AB_add1C_ACB = new Test_23_AB_add1C_ACB();
		printDescription(AB_add1C_ACB.getDescription());
		AB_add1C_ACB.run(LIST_TYPE); 
		totalTests += AB_add1C_ACB.getNumTests(); 
		printLine();
		
		Test_24_AB_add2C_ABC AB_add2C_ABC = new Test_24_AB_add2C_ABC();
		printDescription(AB_add2C_ABC.getDescription());
		AB_add2C_ABC.run(LIST_TYPE); 
		totalTests += AB_add2C_ABC.getNumTests(); 
		printLine();
		
		Test_31_AB_set0C_CB AB_set0C_CB = new Test_31_AB_set0C_CB();
		printDescription(AB_set0C_CB.getDescription());
		AB_set0C_CB.run(LIST_TYPE); 
		totalTests += AB_set0C_CB.getNumTests(); 
		printLine(); 
		
		Test_32_AB_set1C_AC AB_set1C_AC = new Test_32_AB_set1C_AC();
		printDescription(AB_set1C_AC.getDescription());
		AB_set1C_AC.run(LIST_TYPE); 
		totalTests += AB_set1C_AC.getNumTests(); 
		printLine(); 
		
		Test_37_ABC_removeC_AB ABC_removeC_AB = new Test_37_ABC_removeC_AB();
		printDescription(ABC_removeC_AB.getDescription());
		ABC_removeC_AB.run(LIST_TYPE); 
		totalTests += ABC_removeC_AB.getNumTests(); 
		printLine(); 
		
		Test_38_ABC_remove0_BC ABC_remove0_BC = new Test_38_ABC_remove0_BC();
		printDescription(ABC_remove0_BC.getDescription());
		ABC_remove0_BC.run(LIST_TYPE); 
		totalTests += ABC_remove0_BC.getNumTests(); 
		printLine(); 
		
		
		Test_41_ABC_set0D_DBC ABC_set0D_DBC = new Test_41_ABC_set0D_DBC();
		printDescription(ABC_set0D_DBC.getDescription());
		ABC_set0D_DBC.run(LIST_TYPE); 
		totalTests += ABC_set0D_DBC.getNumTests(); 
		printLine();
		
		Test_42_ABC_set1D_ADC ABC_set1D_ADC = new Test_42_ABC_set1D_ADC();
		printDescription(ABC_set1D_ADC.getDescription());
		ABC_set1D_ADC.run(LIST_TYPE); 
		totalTests += ABC_set1D_ADC.getNumTests(); 
		printLine();
		
		Test_43_ABC_set2D_ABD ABC_set2D_ABD = new Test_43_ABC_set2D_ABD();
		printDescription(ABC_set2D_ABD.getDescription());
		ABC_set2D_ABD.run(LIST_TYPE); 
		totalTests += ABC_set0D_DBC.getNumTests(); 
		printLine();

	}
	
	/**
	 * Print test results in a consistent format
	 * 
	 * @param testDesc - description of the test
	 * @param result - indicates whether the test passed or failed
	 */	
	public static void printResults(String testDesc, boolean result)
	{
		total++;
		if (result) 
		{
			passes++;
		} 
		else 
		{
			failures++;
		}
		System.out.printf("%-46s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
	}

	/** 
	 * Print a final summary 
	 */
	private static void printFinalSummary()
	{
		System.out.printf("\nTotal Tests Run: %d of %d,  Passed: %d,  "
				           + "Failed: %d\n", total, totalTests, passes, failures);
	}
	
	/**
	 * Prints description of tests 
	 * @param description - description of tests 
	 */
	private static void printDescription(String description)
	{
		System.out.println(description);		
	}

	/** 
	 * Prints line between tests
	 */
	private static void printLine()
	{
		System.out.println("=======================================================");
		
	}

}
