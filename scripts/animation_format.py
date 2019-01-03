import os


def renameFiles(root, base_name):
    for root, dirs, files in os.walk(root):

        files.sort()
        for i, filename in enumerate(files):
            os.rename(os.path.join(root, filename),
                      os.path.join(root, '{}_{}.png'.format(base_name, i)))

            print(filename)


renameFiles("../src/main/resources/spritesheets/animations", "cd_anim")
