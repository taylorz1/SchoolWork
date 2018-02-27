all: floppya.img

floppya.img: kernel bootload
	echo "Building the image"
	dd if=/dev/zero of=floppya.img bs=512 count=2880 #1>/dev/null
	dd if=bootload of=floppya.img bs=512 count=1 conv=notrunc #1>/dev/null
	dd if=kernel of=floppya.img bs=512 bs=512 conv=notrunc seek=3 #1>/dev/null

kernel: kernel.o kernel_asm.o
	ld86 -o kernel -d kernel.o kernel_asm.o

kernel.o: kernel.c
	bcc -ansi -c -o kernel.o kernel.c

kernel_asm.o: kernel.asm
	as86 kernel.asm -o kernel_asm.o

bootload: bootload.asm
	nasm bootload.asm

clean:
	rm -f *.o *.img kernel bootload bochsout.txt
