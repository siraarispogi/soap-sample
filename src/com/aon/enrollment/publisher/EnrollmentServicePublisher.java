package com.aon.enrollment.publisher;

import javax.xml.ws.Endpoint;

import com.aon.enrollment.EnrollmentService;

public class EnrollmentServicePublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8030/enrollmentservice", new EnrollmentService());
	}
}
