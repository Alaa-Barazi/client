package data;

import java.sql.Date;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

/**
 * Class for saving details for new Order
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int parking_space;

	private int order_number;
	// LocalDate today = LocalDate.of(2024, Month.APRIL, 12);
	private Date order_date;
	private int confirmation_code;
	private int subscriber_id;

	private Date date_of_placing_an_order;

	/**
	 * constructor
	 * @param parking_space value of parking spot 
	 * @param order_number value of parking spot 
	 * @param order_date  value of order date
	 * @param confirmation_code  value of confirmation code 
	 * @param subscriber_id  value of subscriber id
	 * @param date_of_placing_an_order  value of placing order date 
	 */
	public Order(int parking_space, int order_number, Date order_date, int confirmation_code, int subscriber_id,
			Date date_of_placing_an_order) {
		super();
		this.parking_space = parking_space;
		this.order_number = order_number;
		this.order_date = order_date;
		this.confirmation_code = confirmation_code;
		this.subscriber_id = subscriber_id;
		this.date_of_placing_an_order = date_of_placing_an_order;
	}

	/**
	 * @return parking sapce
	 */
	public int getParking_space() {
		return parking_space;
	}

	/**
	 * set new value for param
	 * @param parking_space
	 */
	public void setParking_space(int parking_space) {
		this.parking_space = parking_space;
	}

	/**
	 * @return order number
	 */
	public int getOrder_number() {
		return order_number;
	}

	/**
	 * set new value for param
	 * @param order_number
	 */
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	/**
	 * @return order date
	 */
	public Date getOrder_date() {
		return order_date;
	}

	/**
	 * set new value for param
	 * @param order_date
	 */
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	/**
	 * @return confirmation code
	 */
	public int getConfirmation_code() {
		return confirmation_code;
	}

	/**
	 *  set new value for param
	 * @param confirmation_code
	 */
	public void setConfirmation_code(int confirmation_code) {
		this.confirmation_code = confirmation_code;
	}

	/**
	 * @return subscriber id
	 */
	public int getSubscriber_id() {
		return subscriber_id;
	}

	/**
	 *  set new value for param
	 * @param subscriber_id
	 */
	public void setSubscriber_id(int subscriber_id) {
		this.subscriber_id = subscriber_id;
	}

	/**
	 * @return date of placing an order
	 */
	public Date getDate_of_placing_an_order() {
		return date_of_placing_an_order;
	}

	/**
	 * set new value for param
	 * @param date_of_placing_an_order
	 */
	public void setDate_of_placing_an_order(Date date_of_placing_an_order) {
		this.date_of_placing_an_order = date_of_placing_an_order;
	}

	/**
	 *returns string that contains all information about the order
	 */
	@Override
	public String toString() {
		return "parking_space=" + parking_space + ", order_number=" + order_number + ", order_date=" + order_date
				+ "\n confirmation_code=" + confirmation_code + ", subscriber_id=" + subscriber_id
				+ ", date_of_placing_an_order=" + date_of_placing_an_order + "\n";
	}


}
