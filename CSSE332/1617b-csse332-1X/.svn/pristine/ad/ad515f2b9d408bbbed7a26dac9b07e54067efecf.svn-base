CC = bcc
CFLAGS = -ansi -c
LD = ld86

all: floppya.img

floppya.img: kernel bootload
	echo "Building the image"
	dd if=/dev/zero of=floppya.img bs=512 count=2880 #1>/dev/null
	dd if=bootload of=floppya.img bs=512 count=1 conv=notrunc #1>/dev/null
	dd if=kernel of=floppya.img bs=512 conv=notrunc seek=3 #1>/dev/null
	dd if=message.txt of=floppya.img bs=512 count=1 conv=notrunc seek=30 #1>/dev/null

kernel: kernel.o kernel_asm.o terminal.o floppy_driver.o interrupt_handler.o
	$(LD) -o kernel -d kernel.o kernel_asm.o terminal.o floppy_driver.o interrupt_handler.o

kernel.o: kernel.c terminal.h
	$(CC) $(CFLAGS) -o kernel.o kernel.c

kernel_asm.o: kernel.asm
	as86 kernel.asm -o kernel_asm.o

terminal.o: terminal.c terminal.h
	$(CC) $(CFLAGS) -o terminal.o terminal.c

floppy_driver.o: floppy_driver.c floppy_driver.h
	$(CC) $(CFLAGS) -o floppy_driver.o floppy_driver.c

interrupt_handler.o: interrupt_handler.c interrupt_handler.h
	$(CC) $(CFLAGS) -o interrupt_handler.o interrupt_handler.c

bootload: bootload.asm
	nasm bootload.asm

clean:
	rm -f *.o *.img kernel bootload bochsout.txt
