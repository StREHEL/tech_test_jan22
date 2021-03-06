import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'findLanguageFromKey' })
export class FindLanguageFromKeyPipe implements PipeTransform {
  private languages: { [key: string]: { name: string; rtl?: boolean } } = {
    'ar-ly': { name: 'العربية', rtl: true },
    bn: { name: 'বাংলা' },
    'zh-cn': { name: '中文（简体）' },
    nl: { name: 'Nederlands' },
    en: { name: 'English' },
    fr: { name: 'Français' },
    el: { name: 'Ελληνικά' },
    ja: { name: '日本語' },
    ko: { name: '한국어' },
    es: { name: 'Español' },
    // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
  };

  transform(lang: string): string {
    return this.languages[lang].name;
  }

  isRTL(lang: string): boolean {
    return Boolean(this.languages[lang].rtl);
  }
}
