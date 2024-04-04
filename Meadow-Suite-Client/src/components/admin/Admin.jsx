import React from "react"
import { Link } from "react-router-dom"

const Admin = () => {
	return (
		<section className="container mt-5">
			<h2 className="hotel-t-color">Welcome to Admin Panel</h2>
			<hr />
			<Link className="hotel-sec-color" to={"/existing-rooms"}>Manage Rooms</Link> <br />
			<Link className="hotel-sec-color" to={"/existing-bookings"}>Manage Bookings</Link>
		</section>
	)
}

export default Admin
