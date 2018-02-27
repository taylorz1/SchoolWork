CC = bcc
CFLAGS = -ansi -c
LD = ld86

all: floppya.img

floppya.img: kernel bootload loadFile shell
	@echo "Building the Image"
	@dd if=/dev/zero of=floppya.img bs=512 count=2880 status=none
	@dd if=bootload of=floppya.img bs=512 count=1 conv=notrunc status=none
	@dd if=map.img of=floppya.img bs=512 count=1 seek=1 conv=notrunc status=none
	@dd if=dir.img of=floppya.img bs=512 count=1 seek=2 conv=notrunc status=none
	@dd if=kernel of=floppya.img bs=512 conv=notrunc seek=3 status=none
	@./loadFile shell
	@./loadFile message.txt
	@./loadFile tstprg
	@./loadFile tstpr2

kernel: kernel.o kernel_asm.o terminal.o floppy_driver.o interrupt_handler.o prog.o
	@echo "Linking the Kernel"
	@$(LD) -o kernel -d kernel.o kernel_asm.o terminal.o floppy_driver.o interrupt_handler.o prog.o

kernel.o: kernel.c terminal.h floppy_driver.h interrupt_handler.h
	@echo "Building the Kernel"
	@$(CC) $(CFLAGS) -o kernel.o kernel.c

kernel_asm.o: kernel.asm
	@echo "Building the Kernel (asm)"
	@as86 kernel.asm -o kernel_asm.o

terminal.o: terminal.c terminal.h
	@echo "Building the Terminal Driver"
	@$(CC) $(CFLAGS) -o terminal.o terminal.c

floppy_driver.o: floppy_driver.c floppy_driver.h
	@echo "Building the Floppy Driver"
	@$(CC) $(CFLAGS) -o floppy_driver.o floppy_driver.c

interrupt_handler.o: interrupt_handler.c interrupt_handler.h
	@echo "Building the Interrupt Handler"
	@$(CC) $(CFLAGS) -o interrupt_handler.o interrupt_handler.c

prog.o: prog.c prog.h
	@echo "Building the Program Executer"
	@$(CC) $(CFLAGS) -o prog.o prog.c

bootload: bootload.asm
	@echo "Building the Bootloader"
	@nasm bootload.asm

clean:
	@rm -f *.o floppya.img kernel bootload bochsout.txt loadFile shell

loadFile: loadFile.c
	@echo "Building the Loadfile Utility"
	@gcc -w -o loadFile loadFile.c

test: floppya.img
	@qemu-system-i386 -fda floppya.img

test-bochs: floppya.img
	@bochs -f opsys.bxrc

shell: lib.asm shell.c
	@echo "Building the shell"
	@$(CC) $(CFLAGS) -o shell.o shell.c
	@as86 lib.asm -o lib.o
	@$(LD) -o shell -d shell.o lib.o
