// Verilog test fixture created from schematic C:\Users\taylorz1\Documents\CSSE132\lab02\alu\alu1b.sch - Wed Sep 14 12:49:36 2016

`timescale 1ns / 1ps

module alu1b_alu1b_sch_tb();

// Inputs
   reg a;
   reg b;
   reg ci;
   reg [2:0] op;

// Output
   wire r;
   wire co;

// Bidirs

// Instantiate the UUT
   alu1b UUT (
		.a(a), 
		.b(b), 
		.ci(ci), 
		.r(r), 
		.co(co), 
		.op(op)
   );
// Initialize Inputs
initial begin
  ci = 0;
  b = 0;
  a = 0;
  op[2:0] = 000;
  // Wait 100ns for the simulator to finish initializing
  #100;
  #1;
  if ((r == 0))
    $display("okay and");
  else
    $display("fail 1");
	 
  #100;
  b = 1;
  a = 1;
  #100;
  if ((r==1))
		$display("okay and");
	else
		$display("fail 1");
		
	#100
	op[2:0] = 001;
	a = 0;
	b = 0;
	#100
	if ((r==0))
		$display("okay or");
	else
		$display("fail 1");
		
	
	a = 1;
	b = 0;
	#100
	if ((r==1))
		$display("okay or");
	else
		$display("fail 1");
		
	
	a = 0;
	b = 1;
	#100
	if ((r==1))
		$display("okay or");
	else
		$display("fail 1");
		
	
	a = 1;
	b = 1;
	#100
	if ((r==1))
		$display("okay or");
	else
		$display("fail 1");
		
	
	a = 1;
	b = 0;
	#100
	if ((r==1))
		$display("okay or");
	else
		$display("fail 1");
		
	op[2:0] = 010;
	#100;
  a = 1;
  #1;
  if ((r == 1) && (co == 0))
    $display("okay add");
  else
    $display("fail 1");
	 
  #100;
  a = 1;
  b = 1;
  #1;
  if ((r == 0) && (co == 1))
    $display("okay add");
  else
    $display("fail 1");
	 
  #100;
  a = 0;
  b = 1;
  #1;
  if ((r == 1) && (co == 0))
    $display("okay add");
  else
    $display("fail 1");
	 
  #100;
  a = 0;
  b = 0;
  #1;
  if ((r == 0) && (co == 0))
    $display("okay add");
  else
    $display("fail 1");
	 
  op[2:0] = 110;
  
  #100;
  a = 0;
  b = 0;
  #1;
  if ((r == 1) && (co == 0))
    $display("okay sub_1b");
  else
    $display("fail 1");
  #100;
  a = 0;
  b = 1;
  #1;
  if ((r == 0) && (co == 0))
    $display("okay sub_1b");
  else
    $display("fail 1");
  #100;
  a = 1;
  b = 0;
  #1;
  if ((r == 0) && (co == 1))
    $display("okay sub_1b");
  else
    $display("fail 1");
  #100;
  a = 1;
  b = 1;
  #1;
  if ((r == 1) && (co == 0))
    $display("okay sub_1b");
  else
    $display("fail 1");
end
endmodule
