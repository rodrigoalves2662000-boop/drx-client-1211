from PIL import Image, ImageDraw
import os
A = "src/main/resources/assets/drxclient/textures"

def line(img, pts, col):
    d = ImageDraw.Draw(img); d.line(pts, fill=col, width=1)

def knife(name, blade, edge, curve):
    im = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    line(im, [(2, 14), (6, 10)], (40, 40, 45, 255))          # cabo
    line(im, [(5, 11), (7, 9)], (200, 30, 40, 255))          # guarda DRX
    pts = [(7, 9), (10, 5 - curve), (13, 2)] if curve else [(7, 9), (13, 3)]
    line(im, pts, blade)
    line(im, [(x + 1, y) for x, y in pts], edge)
    if name == "butterfly":
        line(im, [(6, 8), (11, 3)], blade); line(im, [(7, 8), (12, 3)], edge)
    im.save(f"{A}/item/{name}.png")

knife("bayonet", (170, 180, 190, 255), (235, 240, 245, 255), 0)
knife("karambit", (200, 40, 60, 255), (255, 120, 130, 255), 2)
knife("butterfly", (60, 140, 220, 255), (150, 210, 255, 255), 0)

def cape(name, c1, c2):
    im = Image.new("RGBA", (64, 32), (0, 0, 0, 0)); d = ImageDraw.Draw(im)
    for y in range(17):
        t = y / 16
        col = tuple(int(c1[i] * (1 - t) + c2[i] * t) for i in range(3)) + (255,)
        d.line([(1, y + 1), (22, y + 1)], fill=col)
    d.rectangle([0, 0, 22, 0], fill=(0, 0, 0, 255))
    im.save(f"{A}/cape/{name}.png")

cape("drx", (220, 20, 40), (60, 0, 10))
cape("neon", (0, 255, 200), (120, 0, 255))
cape("dark", (35, 35, 40), (5, 5, 8))
