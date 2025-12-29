import asyncio

async def handle_client(reader, writer):
    proc = await asyncio.create_subprocess_exec(
        "stockfish",
        stdin=asyncio.subprocess.PIPE,
        stdout=asyncio.subprocess.PIPE
    )

    async def to_engine():
        while True:
            data = await reader.read(1024)
            if not data: break
            proc.stdin.write(data)
            await proc.stdin.drain()

    async def from_engine():
        while True:
            line = await proc.stdout.readline()
            if not line: break
            writer.write(line)
            await writer.drain()

    await asyncio.gather(to_engine(), from_engine())

async def main():
    server = await asyncio.start_server(handle_client, "0.0.0.0", 5000)
    async with server:
        await server.serve_forever()

if __name__ == "__main__":
    asyncio.run(main())