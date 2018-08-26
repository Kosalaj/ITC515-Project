import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class library implements Serializable {

	/**
	* @author  Chathuranga Muthukumarana.
	* @date   2018-08-24 
	*/
	
	//change veriable LIBRARY_FILE to libraryfile	
	private static final String libraryfile = "library.obj";
	//change veriable LOAN_LIMIT  to loanlimit
	private static final int loanlimit = 2;
	//change veriable LOAN_PERIOD to loanperiod
	private static final int loanperiod = 2;
	//change veriable FINE_PER_DAY to fineperday
	private static final double fineperday = 1.0;
	//change veriable MAX_FINES_OWED to maxfinesowed
	private static final double maxfinesowed = 5.0;
	//change veriable DAMAGE_FEE to damagefee
	private static final double damagefee = 2.0;
	
	private static library self;
	//change veriable BID to bookid
	private int bookid;
	//change veriable MID to memberid
	private int memberid;
	//change veriable LID to loanid
	private int loanid;
	private Date loadDate;
	
	private Map<Integer, book> catalog;
	private Map<Integer, member> members;
	private Map<Integer, loan> loans;
	private Map<Integer, loan> currentLoans;
	private Map<Integer, book> damagedBooks;
	

	private library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		//change veriable BID to bookid
		bookid = 1;
		//change veriable MID to memberid
		memberid = 1;	
		//change veriable LID to loanid	
		loanid = 1;		
	}

	
	public static synchronized library INSTANCE() {		
		if (self == null) {
			Path path = Paths.get(LIBRARY_FILE);			
			if (Files.exists(path)) {	
				try (ObjectInputStream lof = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
					self = (library) lof.readObject();
					Calendar.getInstance().setDate(self.loadDate);
					lof.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new library();
		}
		return self;
	}

	
	public static synchronized void SAVE() {
		if (self != null) {
			self.loadDate = Calendar.getInstance().Date();
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				lof.writeObject(self);
				lof.flush();
				lof.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	//change method BookID to getBookID
	public int getBookID() {
		//change veriable BID to bookid
		return bookid;
	}
	
	//change method MemberID to getMemberID
	public int MemberID() {
		//change veriable MID to memberid
		return memberid;
	}
	
	//change method nextBID to nextBookID 
	private int nextBookID() {
		//change veriable BID to bookid
		return bookid++;
	}
 
	//change method nextMID to nextMemberid 
	private int nextMemberid() {
		//change veriable MID to memberid
		return memberid++;
	}

	//change method nextLID to nextLoanid	
	private int nextLoanid	() {
		//change veriable LID to loanid	
		return loanid++;
	}

	
	public List<member> Members() {		
		return new ArrayList<member>(members.values()); 
	}


	public List<book> Books() {		
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoans.values());
	}

        //change method Add_mem to addmember
	public member addmember(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, nextMID());
		members.put(member.getId(), member);		
		return member;
	}

	//change method Add_book to addBook
	public book addBook(String a, String t, String c) {		
		book b = new book(a, t, c, nextBID());
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public member getMember(int memberId) {
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int loanLimit() {
		//change veriable LOAN_LIMIT  to loanlimit
		return loanlimit;
	}

	
	public boolean memberCanBorrow(member member) {	
		//change veriable LOAN_LIMIT  to loanlimit	
		if (member.getNumberOfCurrentLoans() == loanlimit) 
			return false;
		//change veriable MAX_FINES_OWED to maxfinesowed		
		if (member.getFinesOwed() >= maxfinesowed) 
			return false;
				
		for (loan loan : member.getLoans()) 
			if (loan.isOverDue()) 
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {
		//change veriable LOAN_LIMIT  to loanlimit		
		return loanlimit- member.getNumberOfCurrentLoans();
	}

	
	public loan issueLoan(book book, member member) {
		//change veriable LOAN_PERIOD to loanperiod
		Date dueDate = Calendar.getInstance().getDueDate(loanperiod);
		loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			//change veriable FINE_PER_DAY to fineperday
			double fine = daysOverDue * fineperday;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			//change veriable DAMAGE_FEE to damagefee
			member.addFine(damagefee);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
