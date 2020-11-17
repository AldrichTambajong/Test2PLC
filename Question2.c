#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void createStaticArray(void){
    int staticA[210000];
}

void createStackArray(void){
    int stackA[210000];
}
void createHeapArray(void){
    int *heapA = (int *) malloc(210000 * sizeof(int));
}

main(void){
    struct timespec start;
    struct timespec end;
    int i;

    clock_gettime(CLOCK_REALTIME, &start);
    for(i = 0; i < 120000; i++){
        createStaticArray();
    }
    clock_gettime(CLOCK_REALTIME, &end);
    printf("It took %ld nanoseconds to make the static array\n", end.tv_nsec - start.tv_nsec);

    clock_gettime(CLOCK_REALTIME, &start);
    for(i = 0; i < 120000; i++){
        createStackArray();
    }
    clock_gettime(CLOCK_REALTIME, &end);
    printf("It took %ld nanoseconds to make the stack array\n", end.tv_nsec - start.tv_nsec);

    clock_gettime(CLOCK_REALTIME, &start);
    for(i = 0; i < 120000; i++){
        createHeapArray();
    }
    clock_gettime(CLOCK_REALTIME, &end);
    printf("It took %ld nanoseconds to make the heap array\n", end.tv_nsec-start.tv_nsec);
}
