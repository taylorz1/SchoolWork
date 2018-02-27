// Verilog test fixture created from schematic C:\Users\taylorz1\Documents\CSSE132\lab02\alu\alu.sch - Wed Sep 14 14:09:20 2016

`timescale 1ns / 1ps

module alu_alu_sch_tb();

// Inputs
   reg [3:0] a;
   reg [3:0] b;
   reg [3:0] ci;
   reg [2:0] op;
// Output
   wire [3:0] r;
   wire co; // I think we must have a CO for each ALU
	wire overflow;
	wire zero;
// Bidirs

// Instantiate the UUT
   alu UUT (
		.a(a),
		.b(b),
		.ci(ci),
		.op(op),
		.r(r),
		.co(co),
		.overflow(overflow),
		.zero(zero)
   );
// Initialize Inputs
  initial begin
  ci = 4'b0000;
  b = 4'b0101;
  a = 4'b0110;
  op[2:0] = 3'b001;
  // Wait 100ns for the simulator to finish initializing
  #100;
  #1;
  if ((r == 4'b0111))
    $display("okay or");
  else
    $display("fail or");
	 
  #100;
  ci[0] = 0;
  b = 4'b0101;
  a = 4'b0110;
  op[2:0] = 3'b000;
  #100;
  if ((r == 4'b0100))
		$display("okay and");
	else
		$display("fail and");  
		
  // Addition w/o overflow
  #100;
  ci[0] = 0;
  b = 4'b0010;
  a = 4'b0101;
  op[2:0] = 3'b010;
  #100;
  if ((r == 4'b0111 && overflow == 0)) // Tests addition w/o overflow
		$display("okay ADD");
	else
		$display("fail ADD");
		
  #100;
  ci[0] = 0;
  b = 4'b0110;
  a = 4'b0110;
  op[2:0] = 3'b010;
  #100;
  if ((r == 4'b1100 && overflow == 1)) // Tests addition with overflow, overflow detector should return 1.
		$display("okay ADD");
	else
		$display("fail ADD");
		
  #100;
  ci[0] = 0;
  b = 4'b0110;
  a = 4'b0110;
  op[2:0] = 4'b110;
  #100;
  if ((r == 4'b0000 && overflow == 0 && zero == 1)) // Tests substraction with overflow, overflow detector should return 0, and zero should return 1.
		$display("okay SUB");
	else
		$display("fail SUB");
		
  #100;
  ci[0] = 0;
  b = 4'b0111;
  a = 4'b0110;
  op[2:0] = 3'b110;
  #100;
  if ((r == 4'b1111 && overflow == 0)) // Tests substraction w/o overflow, overflow detector should return 0.
		$display("okay SUB");
	else
		$display("fail SUB");
		
  #100;
  ci[0] = 0;
  b = 4'b0111;
  a = 4'b0110;
  op[2:0] = 3'b111;
  #100;
  if (r == 4'b0001) // Tests less than.
		$display("okay LESS THAN");
	else
		$display("fail LESS THAN");
		
  #100;
  ci[0] = 0;
  b = 4'b0001;
  a = 4'b0110;
  op[2:0] = 3'b111;
  #100;
  if (r == 4'b0000) // Tests less than.
		$display("okay LESS THAN");
	else
		$display("fail LESS THAN");
		
end
endmodule
