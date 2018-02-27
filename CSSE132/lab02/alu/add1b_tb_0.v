// Verilog test fixture created from schematic C:\Users\taylorz1\Documents\CSSE132\lab02\alu\add1b.sch - Tue Sep 13 19:39:56 2016

`timescale 1ns / 1ps

module add1b_add1b_sch_tb();

// Inputs
   reg ci;
   reg b;
   reg a;

// Output
   wire r;
   wire co;

// Bidirs

// Instantiate the UUT
   add1b UUT (
		.ci(ci), 
		.b(b), 
		.a(a), 
		.r(r), 
		.co(co)
   );
// Initialize Inputs
   initial begin
  ci = 0;
  b = 0;
  a = 0;
  // Wait 100ns for the simulator to finish initializing
  #100;
  a = 1;
  #1;
  if ((r == 1) && (co == 0))
    $display("okay 1");
  else
    $display("fail 1");
  #100;
  a = 1;
  b = 1;
  #1;
  if ((r == 0) && (co == 1))
    $display("okay 1");
  else
    $display("fail 1");
  #100;
  a = 0;
  b = 1;
  #1;
  if ((r == 1) && (co == 0))
    $display("okay 1");
  else
    $display("fail 1");
  #100;
  a = 0;
  b = 0;
  #1;
  if ((r == 0) && (co == 0))
    $display("okay 1");
  else
    $display("fail 1");
end
endmodule
