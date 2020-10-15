#include "sndfile.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define WAV 1
#define FLAC 2 

#define SUCCESS 0 
#define FILE_ERROR -1

typedef struct{
    // long frames;
    int samplerate;
    int channels;
    // int format;
} audio_info;

static SNDFILE* sndfile;
static SF_INFO sfinfo;
static audio_info ainfo;


int open_audio(char* filename)
{
    printf("LIBC: Open audio: %s\n", filename);

    memset(&sfinfo, 0, sizeof(sfinfo));

    if(!(sndfile = sf_open(filename, SFM_READ, &sfinfo)))
    {
        return FILE_ERROR;
    }

    ainfo.channels = sfinfo.channels;
    ainfo.samplerate = sfinfo.samplerate;

    // printf("Format %d\n", sfinfo.format);

    return SUCCESS;
}

int close_audio()
{
    sf_close(sndfile);
}

int read_double(double *result, int bsize)
{
    return sf_read_double(sndfile, result, bsize);
}

audio_info get_audio_info()
{
    return ainfo;
}

// int main(int argc, char **argv)
// {   
//     double *buffer = (double*)malloc(sizeof(double)*1024);
//     audio_info info;

//     open_audio("/home/gapeev/Work/Projects/Flac_reader_module/20200203-131000.flac");

//     read_double(buffer, &info, 1024);

//     close_audio();

//     free(buffer);

//     return 0;
// }